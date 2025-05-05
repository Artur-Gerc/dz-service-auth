package ru.dzserviceauth.handlers;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.dzserviceauth.models.User;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String username = webRequest.getParameter("user");
        String password = webRequest.getParameter("password");

        User user = new User(username, password);

        if (binderFactory != null) {
            var binder = binderFactory.createBinder(webRequest, user, "user");
            binder.validate(); // вызов валидации

            var result = binder.getBindingResult();
            if (result.hasErrors()) {
                throw new MethodArgumentNotValidException(parameter, result);
            }
        }

        return user;
    }
}

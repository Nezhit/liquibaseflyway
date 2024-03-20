package cosmonaut.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    CurrentUserUtils currentUserUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("/login".equals(request.getRequestURI())||"/authenticateTheUser".equals(request.getRequestURI())||"/register".equals(request.getRequestURI())||"/logout".equals(request.getRequestURI())) {
            // Для страницы ошибки перехват не нужен, пропускаем запрос
            return true;
        }
        if (checkLogin()) {
            // Продолжаем обработку запроса
            return true;
        } else {
            // Возвращаем страницу ошибки
            response.sendRedirect("/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Метод для дополнительной логики после обработки запроса контроллером
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Метод для логики после полной обработки запроса
    }

    private boolean checkLogin() {
        return currentUserUtils.getCurrentLoggedUser() != null;
    }
}

package leonsk32.myapp.myappback.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.util.StringUtils
import java.util.Objects.isNull
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MySavedRequestAwareAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {
    private var requestCache = HttpSessionRequestCache()

    override fun onAuthenticationSuccess(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authentication: Authentication) {
        val savedRequest = requestCache.getRequest(request, response)

        if (isNull(savedRequest)) {
            clearAuthenticationAttributes(request)
            return
        }

        val targetUrlParam = getTargetUrlParameter()
        if (isAlwaysUseDefaultTargetUrl()
                || (!isNull(targetUrlParam)
                        && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response)
            clearAuthenticationAttributes(request)
            return
        }

        clearAuthenticationAttributes(request)
    }
}
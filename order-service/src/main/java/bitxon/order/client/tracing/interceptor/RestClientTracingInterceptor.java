package bitxon.order.client.tracing.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RestClientTracingInterceptor implements ClientHttpRequestInterceptor {

    private final TracingHeadersProvider tracingHeadersProvider;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        var headers = request.getHeaders();
        tracingHeadersProvider.getTraceHeaders()
            .forEach(headers::add);
        return execution.execute(request, body);
    }
}

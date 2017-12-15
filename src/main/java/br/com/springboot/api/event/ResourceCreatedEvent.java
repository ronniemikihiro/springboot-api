package br.com.springboot.api.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class ResourceCreatedEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private Long id;

    public ResourceCreatedEvent(final Object source, final HttpServletResponse response, final Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getId() {
        return id;
    }
}

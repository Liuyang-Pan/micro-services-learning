package org.example.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * purpose:全局过滤器
 *
 * @author Pan Liuyang
 * 2022/11/21 22:53
 */
@Order(-1) //越小越先执行
@Component //注入到Spring容器
public class AuthorityFilter implements GlobalFilter, Ordered {

    /**
     * 处理当前请求，有必要的话通过{@link GatewayFilterChain}将请求交给下一个过滤器处理
     *
     * @param exchange 请求上下文，里面可以获取Request、Response等信息
     * @param chain    用来把请求委托给下一个过滤器
     * @return 返回标示当前过滤器业务结束
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String authorization = queryParams.getFirst("authorization");
        if ("admin".equals(authorization)) {
            //放行将请求放行给下一个过滤器
            return chain.filter(exchange);
        }
        //返回401状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //拦截，获取详情信息设置终点
        return exchange.getResponse().setComplete();
    }

    /**
     * 实现Ordered接口重写过滤器执行顺序
     *
     * @return 返回数字越小执行顺序越高
     */
    @Override
    public int getOrder() {
        return -1;
    }
}

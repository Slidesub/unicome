package org.unicome.cms.demo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gerald.Wang
 * @date 2018-11-30
 * 设计模式 - 责任链, builder
 */
@Data
public class Request {
    private String name;
    private String reason;
    private int days;

    public Request(RequestBuilder builder) {
        this.name = builder.name;
        this.reason = builder.reason;
        this.days = builder.days;

    }

    @Override
    public String toString() {
        return name + ", " + reason + ", " + days;
    }

    public static class RequestBuilder {
        public String name;
        public String reason;
        public int days;

        public RequestBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RequestBuilder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public RequestBuilder setDays(int days) {
            this.days = days;
            return this;
        }

        public RequestBuilder newRequest(Request request) {
            this.name = request.name;
            this.reason = request.reason;
            this.days = request.days;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }

    public void main(String[] args) {
        Request request = new Request.RequestBuilder().setName("gd").build();
        System.out.println(request.toString());
    }
}

@Data
class Result {
    public boolean isApproved;
    public String info;

    public Result() {

    }
    public Result(boolean isApproved, String info) {
        super();
        this.isApproved = isApproved;
        this.info = info;
    }
    @Override
    public String toString() {
        return isApproved + ", " + info;
    }
}

interface Ratify {
    // 处理请求
    public Result deal(RequestChain chain);
}
interface RequestChain {
    // 获取当前的request
    Request request();
    // 转发leave request
    Result process(Request request);
}

class RequestProcessChain implements RequestChain {
    public Request request;
    public List<Ratify> ratifies;
    public int index;
    public RequestProcessChain(List<Ratify>ratifies, Request request, int index) {
        this.request = request;
        this.ratifies = ratifies;
        this.index = index;
    }
    @Override
    public Result process(Request request) {
        Result result = null;
        if (ratifies.size() > index) {
            RequestChain processChain = new RequestProcessChain(ratifies, request, index + 1);
            Ratify ratify = ratifies.get(index);
            result = ratify.deal(processChain);
        }
        return result;
    }

    @Override
    public Request request() {
        return request;
    }
}

class RequestChainResponsibility {
    private List<Ratify> ratifies;
    public RequestChainResponsibility() {
        ratifies = new ArrayList<Ratify>();
    }
    public RequestChainResponsibility addRatify(Ratify ratify) {
        ratifies.add(ratify);
        // 返回this, 才能实现链式调用
        return this;
    }
    public Result execute(Request request) {
        RequestChain chain = new RequestProcessChain(ratifies, request, 0);
        return chain.process(request);
    }

    public static void main(String[] args) {
        Request request = new Request.RequestBuilder()
                .setName("gd").setReason("请假").setDays(5).build();
        RequestChainResponsibility client = new RequestChainResponsibility();
        client.addRatify(new Manager())
                .addRatify(new Director());
        Result result = client.execute(request);
        System.out.println("结果：" + result);
    }
}

class Manager implements Ratify {
    @Override
    public Result deal(RequestChain chain) {
        Request request = chain.request();
        if (request.getDays() > 1) {
            System.out.println("Manager无法处理, 传递给下一个");
            Request newR = new Request.RequestBuilder().newRequest(request).build();
            return chain.process(newR);
        }
        System.out.println("Manager可以处理");
        return new Result(true, "manager approve");
    }
}

class Director implements Ratify {
    @Override
    public Result deal(RequestChain chain) {
        Request request = chain.request();
        if (request.getDays() > 6) {
            System.out.println("Director无法处理, 传递给下一个");
            Request newR = new Request.RequestBuilder().newRequest(request).build();
            return chain.process(newR);
        }
        System.out.println("Director可以处理");
        return new Result(true, "Director approve");
    }
}

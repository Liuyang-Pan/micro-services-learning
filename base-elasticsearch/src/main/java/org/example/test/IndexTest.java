package org.example.test;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.example.constant.ConstantPool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/25 11:26
 */
@RestController
@RequestMapping("/elasticsearch/index")
@RequiredArgsConstructor
public class IndexTest {

    private RestHighLevelClient client;

    public void init() {
        this.client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.101.88:9200")));
    }

    public void close() throws IOException {
        this.client.close();
    }

    @GetMapping("/create")
    public String createIndex() throws IOException {
        init();
        //创建索引请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("java_client");
        //准备请求的DSL语句
        createIndexRequest.source(ConstantPool.JAVA_CLIENT, XContentType.JSON);
        //发送创建请求
        this.client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        close();
        return "完成初始化！！";
    }

    @GetMapping("/delete")
    public String deleteIndex() throws IOException {
        init();
        //创建索引请求
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("java_client");
        //发送删除请求
        this.client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        close();
        return "完成初始化！！";
    }

    @GetMapping("/exists")
    public String existsIndex() throws IOException {
        init();
        //创建索引请求
        GetIndexRequest getIndexRequest = new GetIndexRequest("java_client");
        //发送获取请求
        boolean exists = this.client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        close();
        return exists ? "获取到索引" : "未获取到索引";
    }
}

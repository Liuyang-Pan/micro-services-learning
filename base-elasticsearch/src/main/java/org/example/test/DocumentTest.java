package org.example.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.example.entity.JavaClientEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/25 15:53
 */
@RestController
@RequestMapping("/elasticsearch/document")
public class DocumentTest {
    private RestHighLevelClient client;
    /**
     * Jackson转换工具
     */
    private final static ObjectMapper mapper = new ObjectMapper();

    public void init() {
        this.client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.101.88:9200")));
    }

    public void close() throws IOException {
        this.client.close();
    }

    @GetMapping("/create")
    public String createDocument() throws IOException {
        init();
        //创建一条数据
        JavaClientEntity javaClientEntity = new JavaClientEntity("1", "第一个文档", "四川省成都市双流区", "创建一下文档");
        //new IndexRequest("java_client").id(javaClientEntity.getId())等同于/java_client/id
        IndexRequest indexRequest = new IndexRequest("java_client").id(javaClientEntity.getId());
        //准备JSON文档
        indexRequest.source(mapper.writeValueAsString(javaClientEntity), XContentType.JSON);
        //发起请求
        this.client.index(indexRequest, RequestOptions.DEFAULT);
        close();
        return "文档创建成功！";
    }

    @GetMapping("/batch")
    public String batchAdd() throws IOException {
        init();
        BulkRequest bulkRequest = new BulkRequest();
        //批量添加数据
        JavaClientEntity javaClientEntityOne = new JavaClientEntity("2", "第二个文档", "四川省成都市双流区", "创建一下文档");
        JavaClientEntity javaClientEntityTwo = new JavaClientEntity("3", "第三个文档", "四川省成都市双流区", "创建一下文档");
        bulkRequest.add(new IndexRequest("java_client").id(javaClientEntityOne.getId()).source(mapper.writeValueAsString(javaClientEntityOne), XContentType.JSON));
        bulkRequest.add(new IndexRequest("java_client").id(javaClientEntityTwo.getId()).source(mapper.writeValueAsString(javaClientEntityTwo), XContentType.JSON));
        //发起请
        this.client.bulk(bulkRequest, RequestOptions.DEFAULT);
        close();
        return "文档创建成功！";
    }

    @GetMapping("/query")
    public String queryDocument() throws IOException {
        init();
        GetRequest getRequest = new GetRequest("java_client", "1");
        //发起请求
        GetResponse documentFields = this.client.get(getRequest, RequestOptions.DEFAULT);
        String sourceAsString = documentFields.getSourceAsString();
        close();
        return sourceAsString;
    }

    @GetMapping("/modify")
    public String modifyDocument() throws IOException {
        init();
        UpdateRequest updateRequest = new UpdateRequest("java_client", "1");
        //需要更新的内容
        updateRequest.doc(
                "name", "修改了文档第一个文档",
                "title", "修改一下文档"
        );
        //发起请求
        this.client.update(updateRequest, RequestOptions.DEFAULT);
        close();
        return "修改成功";
    }

    @GetMapping("/delete")
    public String deleteDocument() throws IOException {
        init();
        DeleteRequest deleteRequest = new DeleteRequest("java_client", "1");
        //发起请求
        this.client.delete(deleteRequest, RequestOptions.DEFAULT);
        close();
        return "删除成功";
    }
}

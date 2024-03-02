package com.pixelTrice.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ElasticSearchQuery {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "polls2";

    public String createOrUpdateDocument(Product poll) throws IOException {
        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(poll.get_id())  // Using _id instead of id
                .document(poll)
        );
        if (response.result().name().equals("Created")) {
            return new StringBuilder("Poll has been successfully created.").toString();
        } else if (response.result().name().equals("Updated")) {
            return new StringBuilder("Poll has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public Product getDocumentById(String pollId) throws IOException {
        GetResponse<Product> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(pollId),
                Product.class
        );

        if (response.found()) {
            return response.source();
        } else {
            return null;
        }
    }

    public String deleteDocumentById(String pollId) throws IOException {
        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(pollId));
        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("Poll with id " + pollId + " has been deleted.").toString(); // Using pollId instead of deleteResponse.id()
        }
        return new StringBuilder("Poll with id " + pollId + " does not exist.").toString(); // Using pollId instead of deleteResponse.id()
    }

    public List<Product> searchAllDocuments() throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, Product.class);
        List<Hit<Product>> hits = searchResponse.hits().hits();
        List<Product> polls = new ArrayList<>();
        for (Hit<Product> hit : hits) {
            Product product = hit.source();
            // Set the _id field of the Product object
            product.set_id(hit.id());
            polls.add(product);
        }
        return polls;
    }
}

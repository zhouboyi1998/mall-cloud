package com.cafe.search.elasticsearch.controller;

import com.cafe.search.elasticsearch.service.ElasticSearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2022/7/27 11:06
 * @Description:
 */
@RestController
@RequestMapping(value = "/elastic")
public class ElasticSearchController {

    private ElasticSearchService elasticSearchService;

    public ElasticSearchController(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @GetMapping(value = "/info")
    public ResponseEntity<SearchResponse> info() throws IOException {
        return ResponseEntity.ok(elasticSearchService.info());
    }
}

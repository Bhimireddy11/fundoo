package com.bridgelabz.fundoonotes.ServiceImplementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.Model.Note;
import com.bridgelabz.fundoonotes.Sevice.ElasticSearchServic.ElasticSearchService;
import com.bridgelabz.fundoonotes.config.ElasticSearchConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ElasticSearchConfig  config;
	private String INDEX;
	private String TYPE;
	@Override
	public String createNote(Note note)
	{

		Map<String, Object> dataMapper = objectMapper.convertValue(note, Map.class);
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, String.valueOf(note.getNoteId())).source(dataMapper);
		IndexResponse indexResponse = null;
		try {
			indexResponse = config.client().index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexResponse.getResult().name();
	}

	@Override
	public List<Note> searchByTitle(String title) {
		SearchRequest searchRequest = new SearchRequest("springboot");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(QueryBuilders.matchQuery("title", title));
		searchRequest.source(sourceBuilder);
		SearchResponse searchresponse = null;
		try {
			searchresponse = config.client().search(searchRequest, RequestOptions.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(getSearchResult(searchresponse).toString());
		return getSearchResult(searchresponse);
	}

	private List<Note> getSearchResult(SearchResponse searchresponse) {
		SearchHit[] searchhits = searchresponse.getHits().getHits();
		List<Note> notes = new ArrayList<>();
		if (searchhits.length > 0) {
			Arrays.stream(searchhits)
					.forEach(hit -> notes.add(objectMapper.convertValue(hit.getSourceAsMap(), Note.class)));
		}
		return notes;
	}

}


package com.example.English.teaching.center.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResultDTO {
    private String title;       
    private String description; 
    private String image;       
    private String url;         
    private String type;        
    private String typeLabel;   
}
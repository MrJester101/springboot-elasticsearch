package com.pixelTrice.elastic;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "polls2")
public class Product {



    @Field(type = FieldType.Nested, name = "option1")
    private Option option1;

    @Field(type = FieldType.Nested, name = "option2")
    private Option option2;

    @Field(type = FieldType.Nested, name = "option3")
    private Option option3;

    @Field(type = FieldType.Nested, name = "option4")
    private Option option4;

    @Field(type = FieldType.Text, name = "question")
    private String question;

    @Field(type = FieldType.Keyword, name = "tags")
    private List<String> tags;


    @Field(type = FieldType.Text, name = "Media_url")
    @JsonProperty("Media_url")
    private String MediaUrl;

    @Field(type = FieldType.Text, name = "User_url")
    @JsonProperty("User_url")
    private String UserUrl;

    @Field(type = FieldType.Text, name = "Extracted_from")
    @JsonProperty("Extracted_from")
    private String ExtractedFrom;

    @Field(type = FieldType.Keyword, name = "ES_Uid")
    @JsonProperty("ES_Uid")
    private String EsUid;

    @Field(type = FieldType.Text, name = "Time")
    @JsonProperty("Time")
    private String Time;

    @Field(type = FieldType.Integer, name = "Total_Interaction")
    @JsonProperty("Total_Interaction")
    private int TotalInteraction;

    // Getters and setters

    @Id
    private String _id;

    // Other fields and annotations remain the same

    // Getters and setters

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Option getOption1() {
        return option1;
    }

    public void setOption1(Option option1) {
        this.option1 = option1;
    }

    public Option getOption2() {
        return option2;
    }

    public void setOption2(Option option2) {
        this.option2 = option2;
    }

    public Option getOption3() {
        return option3;
    }

    public void setOption3(Option option3) {
        this.option3 = option3;
    }

    public Option getOption4() {
        return option4;
    }

    public void setOption4(Option option4) {
        this.option4 = option4;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getMediaUrl() {
        return MediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.MediaUrl = mediaUrl;
    }

    public String getUserUrl() {
        return UserUrl;
    }

    public void setUserUrl(String userUrl) {
        this.UserUrl = userUrl;
    }

    public String getExtractedFrom() {
        return ExtractedFrom;
    }

    public void setExtractedFrom(String extractedFrom) {
        this.ExtractedFrom = extractedFrom;
    }

    public String getEsUid() {
        return EsUid;
    }

    public void setEsUid(String esUid) {
        this.EsUid = esUid;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public int getTotalInteraction() {
        return TotalInteraction;
    }

    public void setTotalInteraction(int totalInteraction) {
        this.TotalInteraction = totalInteraction;
    }
}

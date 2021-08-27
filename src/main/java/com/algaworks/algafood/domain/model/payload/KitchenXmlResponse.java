package com.algaworks.algafood.domain.model.payload;

import com.algaworks.algafood.domain.model.Kitchen;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "kitchens")
public class KitchenXmlResponse {

    @NonNull
    @JsonProperty("kitchen")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Kitchen> kitchenList;
}

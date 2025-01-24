package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ProductSearchRequestDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-01-23T16:49:06.948148200+05:30[Asia/Calcutta]", comments = "Generator version: 7.9.0")
public class ProductSearchRequestDTO {

  private String searchTerm;

  private Long categoryId;

  private Double minPrice;

  private Double maxPrice;

  /**
   * Gets or Sets sortBy
   */
  public enum SortByEnum {
    NAME("name"),
    
    PRICE("price"),
    
    DATE("date");

    private String value;

    SortByEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SortByEnum fromValue(String value) {
      for (SortByEnum b : SortByEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private SortByEnum sortBy;

  /**
   * Gets or Sets sortDirection
   */
  public enum SortDirectionEnum {
    ASC("asc"),
    
    DESC("desc");

    private String value;

    SortDirectionEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SortDirectionEnum fromValue(String value) {
      for (SortDirectionEnum b : SortDirectionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private SortDirectionEnum sortDirection;

  private Integer page = 0;

  private Integer size = 20;

  public ProductSearchRequestDTO searchTerm(String searchTerm) {
    this.searchTerm = searchTerm;
    return this;
  }

  /**
   * Get searchTerm
   * @return searchTerm
   */
  
  @Schema(name = "searchTerm", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("searchTerm")
  public String getSearchTerm() {
    return searchTerm;
  }

  public void setSearchTerm(String searchTerm) {
    this.searchTerm = searchTerm;
  }

  public ProductSearchRequestDTO categoryId(Long categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  /**
   * Get categoryId
   * @return categoryId
   */
  
  @Schema(name = "categoryId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categoryId")
  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public ProductSearchRequestDTO minPrice(Double minPrice) {
    this.minPrice = minPrice;
    return this;
  }

  /**
   * Get minPrice
   * @return minPrice
   */
  
  @Schema(name = "minPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minPrice")
  public Double getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Double minPrice) {
    this.minPrice = minPrice;
  }

  public ProductSearchRequestDTO maxPrice(Double maxPrice) {
    this.maxPrice = maxPrice;
    return this;
  }

  /**
   * Get maxPrice
   * @return maxPrice
   */
  
  @Schema(name = "maxPrice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maxPrice")
  public Double getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(Double maxPrice) {
    this.maxPrice = maxPrice;
  }

  public ProductSearchRequestDTO sortBy(SortByEnum sortBy) {
    this.sortBy = sortBy;
    return this;
  }

  /**
   * Get sortBy
   * @return sortBy
   */
  
  @Schema(name = "sortBy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sortBy")
  public SortByEnum getSortBy() {
    return sortBy;
  }

  public void setSortBy(SortByEnum sortBy) {
    this.sortBy = sortBy;
  }

  public ProductSearchRequestDTO sortDirection(SortDirectionEnum sortDirection) {
    this.sortDirection = sortDirection;
    return this;
  }

  /**
   * Get sortDirection
   * @return sortDirection
   */
  
  @Schema(name = "sortDirection", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sortDirection")
  public SortDirectionEnum getSortDirection() {
    return sortDirection;
  }

  public void setSortDirection(SortDirectionEnum sortDirection) {
    this.sortDirection = sortDirection;
  }

  public ProductSearchRequestDTO page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   * minimum: 0
   * @return page
   */
  @Min(0) 
  @Schema(name = "page", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("page")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public ProductSearchRequestDTO size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * minimum: 1
   * maximum: 100
   * @return size
   */
  @Min(1) @Max(100) 
  @Schema(name = "size", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("size")
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductSearchRequestDTO productSearchRequestDTO = (ProductSearchRequestDTO) o;
    return Objects.equals(this.searchTerm, productSearchRequestDTO.searchTerm) &&
        Objects.equals(this.categoryId, productSearchRequestDTO.categoryId) &&
        Objects.equals(this.minPrice, productSearchRequestDTO.minPrice) &&
        Objects.equals(this.maxPrice, productSearchRequestDTO.maxPrice) &&
        Objects.equals(this.sortBy, productSearchRequestDTO.sortBy) &&
        Objects.equals(this.sortDirection, productSearchRequestDTO.sortDirection) &&
        Objects.equals(this.page, productSearchRequestDTO.page) &&
        Objects.equals(this.size, productSearchRequestDTO.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(searchTerm, categoryId, minPrice, maxPrice, sortBy, sortDirection, page, size);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductSearchRequestDTO {\n");
    sb.append("    searchTerm: ").append(toIndentedString(searchTerm)).append("\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    minPrice: ").append(toIndentedString(minPrice)).append("\n");
    sb.append("    maxPrice: ").append(toIndentedString(maxPrice)).append("\n");
    sb.append("    sortBy: ").append(toIndentedString(sortBy)).append("\n");
    sb.append("    sortDirection: ").append(toIndentedString(sortDirection)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


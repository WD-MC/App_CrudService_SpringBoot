package com.example.registration.employee.equipment.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * AccountNotFoundError
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-24T12:07:16.384698600+04:00[Asia/Dubai]", comments = "Generator version: 7.8.0")
public class EquipmentNotFoundError {

  private String message;

  public EquipmentNotFoundError message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */

  @Schema(name = "message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EquipmentNotFoundError equipmentNotFoundError = (EquipmentNotFoundError) o;
    return Objects.equals(this.message, equipmentNotFoundError.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountNotFoundError {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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


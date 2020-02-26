package my.cwm.soil;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * DataTablesInput
 */
@Data
public class DataTablesInput {
    @NotNull
    private int pageNumber;
    @NotNull
    private int pageSize;
    @NotNull
    private String sortColumn;
    @NotNull
    private String sortDirection;
    @NotNull
    private String searchInput;
}
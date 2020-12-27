package ftn.upp.literary.association.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenresDto {

    private String processInstanceId;
    private String taskid;
    private Map<String, String> genres;     // FixMe
}

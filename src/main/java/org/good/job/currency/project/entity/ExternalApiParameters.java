package org.good.job.currency.project.entity;

import lombok.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ExternalApiParameters {

    private String externalApiName;
    private Currency currency;
    private LocalDate date;

    public HashMap<String, String> getParametersHashMap() {
        Class<?> parametersClass = this.getClass();
        Field[] fields = parametersClass.getDeclaredFields();
        HashMap<String, String> parametersHashMap = new HashMap<>();
        for (Field field : fields) {
            String fieldName = field.getName();
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (fieldValue != null) {
                parametersHashMap.put(fieldName, fieldValue.toString());
            }
        }
        return parametersHashMap;
    }
}

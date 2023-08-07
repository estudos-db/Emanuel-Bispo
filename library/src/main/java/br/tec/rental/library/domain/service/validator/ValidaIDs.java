package br.tec.rental.library.domain.service.validator;

import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ValidaIDs {
    public List<Long> execute(List<Long> idRequestList, Iterator<Long> idEntityList) {

        if (!idEntityList.hasNext()) return idRequestList;

        List<Long> iDsInvalidos = idRequestList.stream().sorted()
                .map(
                        idRequest -> {
                            if (!idEntityList.hasNext()) return idRequest;
                            Long proximo = null;

                            if (idRequest != idEntityList.next()) {
                                proximo = idRequest;
                            }

                            return proximo;
                        })
                .collect(Collectors.toList());

        iDsInvalidos.removeIf(Objects::isNull);

        if (iDsInvalidos.isEmpty()) return null;

        return iDsInvalidos;
    }
}

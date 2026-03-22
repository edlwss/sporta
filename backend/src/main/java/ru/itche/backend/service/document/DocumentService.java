package ru.itche.backend.service.document;

import ru.itche.backend.dto.document.NewDocumentPayload;
import ru.itche.backend.entity.Document;

import java.util.List;

public interface DocumentService {

    List<Document> findAllDocUser(Long id);
    Document createDoc(Long userId, NewDocumentPayload payload);

}

package ru.itche.backend.service.document;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itche.backend.dto.document.NewDocumentPayload;
import ru.itche.backend.entity.Document;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.repository.document.DocRepository;
import ru.itche.backend.service.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceDef implements DocumentService {

    private final DocRepository docRepository;
    private final UserService userService;

    @Override
    public List<Document> findAllDocUser(Long id) {
        return null;
    }

    @Override
    public Document createDoc(Long userId, NewDocumentPayload payload) {

        User user = userService.findById(userId);

        return docRepository.save(new Document(null,
                user,
                payload.doc()));
    }
}

package MainApp.services;

import MainApp.dto.WorldObject.*;

public interface WorldObjectService {
    CreateWorldObjectResponse insert(CreateWorldObjectRequest request);

    GetWorldObjectResponse exists(GetWorldObjectRequest request);

    DeleteWorldObjectResponse delete(DeleteWorldObjectRequest request);

    UpdateWorldObjectResponse update(UpdateWorldObjectRequest request);
}
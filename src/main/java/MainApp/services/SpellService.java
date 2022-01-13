package MainApp.services;

import MainApp.dto.Spell.*;

public interface SpellService {
    CreateSpellResponse insert(CreateSpellRequest request);

    GetSpellResponse exists(GetSpellRequest request);

    DeleteSpellResponse delete(DeleteSpellRequest request);

    UpdateSpellResponse update(UpdateSpellRequest request);
}
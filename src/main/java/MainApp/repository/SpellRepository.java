package MainApp.repository;

import MainApp.model.Spell;

public interface SpellRepository {
    String insert(Spell spell);

    String exists(Spell spell);

    boolean delete(Spell spell);

    String update(Spell oldSpell, Spell newSpell);
}
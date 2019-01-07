package mate.academy.dao;

import mate.academy.model.Skills;

public interface SkillsDao {
    Skills getSkillsIdByDevName(String name);
}

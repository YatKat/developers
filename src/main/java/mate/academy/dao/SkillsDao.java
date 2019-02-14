package mate.academy.dao;

import mate.academy.model.Skills;

public interface SkillsDao {
    Skills getSkillsById(int id);
    int createSkill(Skills skills);
    int getSkillIdByLangAndLevel(String lang, String level);
}

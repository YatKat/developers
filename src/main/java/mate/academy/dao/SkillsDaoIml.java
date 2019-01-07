package mate.academy.dao;

import mate.academy.model.Skills;

import java.sql.Connection;

public class SkillsDaoIml extends AbstractDao implements SkillsDao{

    public SkillsDaoIml(Connection connection){
        super(connection);
    }

    @Override
    public Skills getSkillsIdByDevName(String name) {
        return null;
    }
}

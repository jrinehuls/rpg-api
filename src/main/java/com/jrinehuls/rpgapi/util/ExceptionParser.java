package com.jrinehuls.rpgapi.util;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

public class ExceptionParser {

    public static String getUniqueConstraintField(DataIntegrityViolationException ex, Class<?> mcClass) {
        String className = mcClass.getSimpleName().toUpperCase();
        String constraintText = ((ConstraintViolationException) ex.getCause()).getConstraintName();
        String right = constraintText.split(className + "\\(", 2)[1];
        String field = right.split(" NULLS FIRST", 2)[0];
        return capsSnakeToCamel(field);
    }

    /*
    * Comes in like MAGIC_ATTACK or NAME
    * Return magicAttack or name
    * */
    private static String capsSnakeToCamel(String capsSnake) {
        String snake = capsSnake.toLowerCase();
        if (snake.contains("_")) {
            String[] words = snake.split("_");
            StringBuilder camel = new StringBuilder(words[0]);
            for (int i = 1; i < words.length; i++) {
                camel.append(StringUtils.capitalize(words[i]));
            }
            return camel.toString();
        } else {
            return snake;
        }
    }
}

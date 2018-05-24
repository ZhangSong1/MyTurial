package com.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistence.entity.ShortcutEntity;

public interface ShortcutDao extends JpaRepository<ShortcutEntity, Integer>
{

}

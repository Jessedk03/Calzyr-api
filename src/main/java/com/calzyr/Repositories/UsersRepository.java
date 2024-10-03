package com.calzyr.Repositories;

import com.calzyr.Dtos.UsersModel;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UsersModel, Integer> {
}

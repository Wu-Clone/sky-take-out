package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);


    // 批量保存套餐和菜品关联关系
    void insertBatch(List<SetmealDish> setmealDishes);

    @Delete("DELETE from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);
}

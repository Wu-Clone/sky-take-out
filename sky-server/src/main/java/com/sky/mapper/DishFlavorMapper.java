package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品的id 删除菜品口味
     * @param DishIds
     */
    void deleteByDishIds(List<Long> DishIds);

    // 根据菜品id和对饮口味数据
    @Select("select  * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long id);

    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long id);
}

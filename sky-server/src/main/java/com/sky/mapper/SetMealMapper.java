package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{id}")
    Integer countByCategoryId(Long id);

    /**
     * 通过dish id查询对应套餐的id （批量操作）
     * @param ids
     * @return
     */
    List<Long> getSetMealIdsByDishIds(List<Long> ids);
}

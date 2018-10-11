package guru.springfamework.spring5mvcrest;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "Fried Foods";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
        
    }

    @Test
    public void categoryMapperTest() {
        // given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        // when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
        // then
        assertEquals(category.getName(),categoryDTO.getName());
        assertEquals(category.getId(),categoryDTO.getId());
    }
}

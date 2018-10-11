package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.services.CategoryService;
import guru.springfamework.services.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private final Long ID = 1L;
    private final String NAME = "Joe";

    private CategoryService categoryService;

    @Mock
    CategoryMapper categoryMapper;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE,categoryRepository);
    }

    @Test
    public void testGetAll() throws Exception {
        // given
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        categories.add(category);
        categories.add(category);
        categories.add(category);
        // when
        when(categoryRepository.findAll()).thenReturn(categories);
        // then
        List<CategoryDTO> returnedCategories = categoryService.getAllCategories();
        assertEquals(3L,returnedCategories.size());
        verify(categoryRepository,times(1)).findAll();
    }

    @Test
    public void testGetCategoryByName() throws Exception {
        // given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        // when
        when(categoryRepository.findCategoryByNameIgnoreCase(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
        // then
        assertEquals(NAME,categoryDTO.getName());
        assertEquals(ID,categoryDTO.getId());
        verify(categoryRepository,times(1)).findCategoryByNameIgnoreCase(anyString());
    }
}

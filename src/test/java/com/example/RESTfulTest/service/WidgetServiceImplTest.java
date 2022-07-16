package com.example.RESTfulTest.service;

import com.example.RESTfulTest.model.Widget;
import com.example.RESTfulTest.repository.WidgetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class WidgetServiceImplTest {

    @MockBean
    private WidgetRepository repository;

    @Autowired
    private WidgetServiceImpl widgetService;


    @Test
    void findAll() {
        //arrange
        var widget1 = new Widget(1L, "Widget1", "Descripcion1", 1);
        var widget2 = new Widget(2L, "Widget2", "Descripcion2", 2);

        var widgets = Arrays.asList(widget1, widget2);
        Mockito.when(repository.findAll()).thenReturn(widgets);

        //act
        var resultado = widgetService.findAll();

        //assert
        Assertions.assertEquals(widgets.size(), resultado.size());
        Assertions.assertEquals(widget1.getName(), resultado.get(0).getName());
        Assertions.assertEquals(widget2.getName(), resultado.get(1).getName());
    }

    @Test
    void save() {
        //arrange
        var widget = new Widget(1L, "Widget", "Descripcion", 46587);
        Mockito.when(repository.save(any())).thenReturn(widget);

        //act
        var resultado = widgetService.save(widget);

        //assert
        Assertions.assertEquals(widget.getId(), resultado.getId());
        Assertions.assertEquals(widget.getName(), resultado.getName());
        Assertions.assertEquals(widget.getDescription(), resultado.getDescription());
        Assertions.assertEquals(widget.getVersion(), resultado.getVersion());
    }
}
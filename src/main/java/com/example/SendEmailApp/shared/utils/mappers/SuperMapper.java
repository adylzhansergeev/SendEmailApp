package com.example.SendEmailApp.shared.utils.mappers;

import com.example.SendEmailApp.models.basics.BasicModel;
import java.util.List;
public interface SuperMapper<E extends BasicModel, D> {
    public D toDto(E e);
    public E toEntity(D d);
    public List<D> toDtoList(List<E> eList);
    public List<E> toEntityList(List<D> eList);
}

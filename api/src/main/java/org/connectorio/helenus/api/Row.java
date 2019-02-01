package org.connectorio.helenus.api;

public interface Row<C extends Context> {

    <J> J get(Column<C, J> column);

}

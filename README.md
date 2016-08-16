# Perseus

This project is a column store API which is intended to cover details of underlying database backend via simple Column interface. This means that there are few very generic structures:

 * Column
   * WrapperColumn
     * NamedColumn
     * MappedColumn
     * CompositeColumn
 * Container
   * Table

By default Column has only Java type attached and in principle only Java type is required to create first column implementation. Even column naming is not enforced for some stores which do not support that.
Be aware that enforced model requires decoration and wrapping of columns instead of extending base class.
package vn.core.data.model

import vn.core.domain.BaseModel

abstract class BaseRaw {
    abstract fun raw2Model(): BaseModel?
}

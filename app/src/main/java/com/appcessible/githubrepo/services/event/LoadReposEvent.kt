package com.appcessible.githubrepo.services.event

import com.appcessible.githubrepo.models.Repository

class LoadReposEvent : BusEvent<List<Repository>?>() {
    override var data: List<Repository>? = null
        private set

    override fun setData(data: List<Repository>?): BusEvent<*> {
        this.data = data
        return this
    }

    override val type: EventType
        get() = EventType.REPOS
}
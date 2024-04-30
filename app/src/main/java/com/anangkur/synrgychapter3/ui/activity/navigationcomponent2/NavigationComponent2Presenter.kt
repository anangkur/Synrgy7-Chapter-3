package com.anangkur.synrgychapter3.ui.activity.navigationcomponent2

class NavigationComponent2Presenter(
    private val contract: NavigationComponent2ViewContract,
) {

    fun fetchData() {
        try {
            contract.onLoading(true)
            // do something
            contract.onLoading(false)
            contract.onDataReceived(emptyList())
        } catch (error: Throwable) {
            contract.onLoading(false)
            contract.onDataError(error)
        }
    }
}
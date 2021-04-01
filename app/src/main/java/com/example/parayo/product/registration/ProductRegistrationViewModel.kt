package com.example.parayo.product.registration

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.parayo.product.category.categoryList
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductRegistrationViewModel(app: Application) :
        BaseViewModel(app) {

    // 이미지 업로드 후에 반환받은 이미지 주소, 자동으로 이미지를 표시해주도록 데이터바인딩을 이용하기 위해서 List<MutableLiveData<String?>>으로 선언
    val imageUrls: List<MutableLiveData<String?>> = listOf(
        MutableLiveData(null as String?),
        MutableLiveData(null as String?),
        MutableLiveData(null as String?),
        MutableLiveData(null as String?)
    )

    val imageIds: MutableList<Long?> =
        mutableListOf(null, null, null, null)

    val productName = MutableLiveData("")
    val description = MutableLiveData("")
    val price = MutableLiveData("")
    val categories = MutableLiveData(categoryList.map { it.name })
    var categoryIdSelected: Int? = categoryList[0].id

    val descriptionLimit = 500
    val productNameLimit = 40

    val productNameLength = MutableLiveData("0/$productNameLimit")
    val descriptionLength = MutableLiveData("0/$descriptionLimit")

    fun checkProductNameLength() {
        productName.value?.let {
            if(it.length > productNameLimit) {
                productName.value = it.take(productNameLimit)
            }
            productNameLength.value =
                "${productName.value?.length}/$productNameLimit"
        }
    }

    fun checkDescriptionLength() {
        description.value?.let {
            if(it.length > descriptionLimit) {
                description.value = it.take(descriptionLimit)
            }
            descriptionLength.value =
                "${description.value?.length}/$descriptionLimit"
        }
    }

    fun onCategorySelect(position: Int) {
        categoryIdSelected = categoryList[position].id
    }
}
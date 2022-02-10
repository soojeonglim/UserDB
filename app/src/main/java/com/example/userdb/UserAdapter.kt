package com.example.userdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// ArrayList<User> : User클래스를 불러와서 리스트화 시킴, RecyclerView.Adapter : RecyclerView에서 Adapter 속성 불러옴
class UserAdapter (val profileList: ArrayList<User> ) : RecyclerView.Adapter<UserAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.CustomViewHolder {
        // .context : 액티비티에서 담고 있는 모든 정보, parent.context : 어댑터랑 연결될 액티비의 context를 가져옴
        // .inflate : 붙이다, parent : 두번째 속성
        // view는 list_item에 대한것을 끌고와서 어댑터에 붙여주는 역할을 함
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false )
        // 결과적으로 CustomViewHolder(view)가 실행되면 아래있는 itemView가 view가 됨
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    // onCreateViewHolder로 만들어진 view를 가져다가 Bind(연결해줌)
    override fun onBindViewHolder(holder: UserAdapter.CustomViewHolder, position: Int) {
        //실제 연결 해주는 부분
        holder.id.text = profileList.get(position).id
        holder.guide_dog.text = profileList.get(position).guide_dog.toString()
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {    // View에 대해서 꽉 잡아주는 역할
        val id = itemView.findViewById<TextView>(R.id.tv_name) // 이메일, .findViewById : 특정 xml에서 id값들을 찾아올 수 있음
        val guide_dog = itemView.findViewById<TextView>(R.id.tv_dog) // 안내견 유무
    }

    fun setData(user: List<User>){
        this.profileList = user
        notifyDataSetChanged()
    }
}
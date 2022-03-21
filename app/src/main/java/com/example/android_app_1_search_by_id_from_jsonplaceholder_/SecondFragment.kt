package com.example.android_app_1_search_by_id_from_jsonplaceholder_

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android_app_1_search_by_id_from_jsonplaceholder_.api.UserAPIService
import com.example.android_app_1_search_by_id_from_jsonplaceholder_.databinding.FragmentSecondBinding
import com.example.android_app_1_search_by_id_from_jsonplaceholder_.model.User
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val userAPIService = UserAPIService.create()
    var id:String = "1"



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = userAPIService.getUser(id);

        user.enqueue(object :  retrofit2.Callback<User> {   //I have to put retrofit2.callBack???????? why
            override fun onResponse(call: Call<User>, response: Response<User>) {

                val body = response.body()
                body?.let {
               //     Log.i("SecondFragment", it.name)
                    binding.textviewSecond.text = it.email
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }

        })


        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
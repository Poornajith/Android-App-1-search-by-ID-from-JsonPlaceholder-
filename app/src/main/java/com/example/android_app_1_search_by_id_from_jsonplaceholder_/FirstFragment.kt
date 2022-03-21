package com.example.android_app_1_search_by_id_from_jsonplaceholder_

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android_app_1_search_by_id_from_jsonplaceholder_.api.UserAPIService
import com.example.android_app_1_search_by_id_from_jsonplaceholder_.databinding.FragmentFirstBinding
import com.example.android_app_1_search_by_id_from_jsonplaceholder_.model.User
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val userAPIService = UserAPIService.create()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
          //  findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            val idIn = binding.idIn.editableText
            val user = userAPIService.getUser(idIn.toString());

           // Log.i("Text input", id)

            user.enqueue(object :  retrofit2.Callback<User> {   //I have to put retrofit2.callBack???????? why
                override fun onResponse(call: Call<User>, response: Response<User>) {

                    val body = response.body()
                    body?.let {
                        //     Log.i("SecondFragment", it.name)
                        binding.textviewFirst.text = it.email
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.i("FirstFragment", t.message!!)
                }

            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
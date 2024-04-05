package com.anangkur.synrgychapter3.activity.navigationcomponent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anangkur.synrgychapter3.databinding.FragmentSecondNavigationBinding

class SecondNavigationFragment : Fragment() {

    private lateinit var binding: FragmentSecondNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSecondNavigationBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGoToThird.setOnClickListener { validateName() }
    }

    /**
     * Navigates from the second fragment to the third fragment, passing data.
     *
     * This function initiates a navigation action from the second fragment to the third fragment
     * within the navigation graph. It constructs a navigation action using the generated NavDirections
     * class (SecondNavigationFragmentDirections) and sets the data to be passed to the third fragment.
     * The data, in this case, is obtained from the EditText component (etName) of the current fragment's binding.
     * Finally, it uses the NavController associated with the current fragment to perform the navigation
     * with the constructed action.
     */
    private fun goToThirdFragment() {
        val actionToThirdFragment = SecondNavigationFragmentDirections.actionSecondNavigationFragmentToThirdNavigationFragment()
        actionToThirdFragment.name = binding.etName.text.toString()
        findNavController().navigate(actionToThirdFragment)
    }

    /**
     * Validates the input name field and proceeds accordingly.
     *
     * This function checks whether the name input field is empty or not.
     * If the name field is empty, it sets an error message indicating that the field cannot be empty.
     * Otherwise, if the name field is not empty, it clears any existing error message
     * and proceeds to navigate to the third fragment.
     */
    private fun validateName() {
        if (binding.etName.text.isNullOrEmpty()) {
            setErrorName("tidak boleh kosong")
        } else {
            setErrorName(null)
            goToThirdFragment()
        }
    }

    /**
     * Sets an error message for the name input field.
     *
     * This function enables or disables the error state for the name input field based on
     * whether the provided error message is null or empty. If the error message is not null
     * or not empty, it enables the error state and sets the error message to be displayed.
     * Otherwise, if the error message is null or empty, it disables the error state.
     *
     * @param error The error message to be displayed for the name input field. It can be null or empty
     *              to clear any existing error message and disable the error state.
     */
    private fun setErrorName(error: String?) {
        binding.tilName.isErrorEnabled = !error.isNullOrEmpty()
        binding.tilName.error = error
    }
}
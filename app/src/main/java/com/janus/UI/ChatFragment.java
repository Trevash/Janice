package com.janus.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.janus.Presenter.ChatFragmentPresenter;
import com.janus.R;
import com.bignerdranch.android.shared.models.*;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment implements ChatFragmentPresenter.View {

    private ChatFragmentPresenter presenter;

    private RecyclerView mChatList;
    private ChatListAdapter mChatAdapter;
    private EditText mChatMessage;
    private Button mSendButton;

    private List<chatMessageModel> chats = new ArrayList<>();

    public ChatFragment() {
        presenter = new ChatFragmentPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        /* For Testing with Dummy Information
        usernameModel uModel;
        usernameModel uModelOne;

        try {
            uModel = new usernameModel("Blastoise");
            uModelOne = new usernameModel("Charazard");
            chats.add(new chatMessageModel(uModel, "Hullo my friend!"));
            chats.add(new chatMessageModel(uModelOne, "Hullo my Amigo that is super awesome and cool!"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        */

        chats = presenter.getChats();

        mChatList = v.findViewById(R.id.chat_RecyclerView);
        mChatList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mChatAdapter = new ChatListAdapter(chats);
        mChatList.setAdapter(mChatAdapter);

        mChatMessage = v.findViewById(R.id.message_EditText);
        mChatMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updateChatMessage(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mSendButton = v.findViewById(R.id.send_Button);
        mSendButton.setEnabled(false);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendClicked();
            }
        });

        return v;
    }

    public void updatePresenter() {
        presenter.setFragment();
    }

    public void updateSendButton(boolean isActive) {
        mSendButton.setEnabled(isActive);
    }

    public void updateUI() {
        // can be null if the game activity turned back to the first activity
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    //mChatAdapter.notifyItemRangeRemoved(0, chats.size());
                    chats = presenter.getChats();
                    //mChatAdapter.notifyItemRangeInserted(0, chats.size());

                    mChatAdapter = new ChatFragment.ChatListAdapter(chats);
                    mChatList.setAdapter(mChatAdapter);
                }
            });
        } else {
            System.out.println("Error: ChatFragment got a null activity in updateUI()");
        }
    }

    public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder> {
        List<chatMessageModel> chats;

        private class ChatViewHolder extends RecyclerView.ViewHolder {

            private chatMessageModel chat;
            private TextView mUserName;
            private TextView mMessage;

            public ChatViewHolder(RelativeLayout r) {
                super(r);

                mUserName = (TextView) r.findViewById(R.id.userName_TextView);
                mMessage = (TextView) r.findViewById(R.id.message_TextView);
            }
        }

        public ChatListAdapter(List<chatMessageModel> chats) {
            this.chats = chats;
        }

        @Override
        public ChatListAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent, int position) {
            RelativeLayout r = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_child, parent, false);
            return new ChatViewHolder(r);
        }

        @Override
        public void onBindViewHolder(ChatViewHolder holder, int position) {
            chatMessageModel chat = chats.get(position);

            holder.mUserName.setText(chat.getUsername().getValue() + ": ");
            holder.mMessage.setText(chat.getMessage());
        }

        @Override
        public int getItemCount() {
            return chats.size();
        }
    }
}

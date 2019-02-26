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

import java.util.List;

public class ChatFragment extends Fragment implements ChatFragmentPresenter.View {

    private ChatFragmentPresenter presenter;
    private LinearLayoutManager layoutManager;
    private ChatListAdapter adapter;

    private RecyclerView mChatList;
    private EditText mChatMessage;
    private Button mSendButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        presenter = new ChatFragmentPresenter(this);
        presenter.setFragment();



        mChatMessage = (EditText) v.findViewById(R.id.message_EditText);
        mChatMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updateChatMessage(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mSendButton = (Button) v.findViewById(R.id.send_Button);
        mSendButton.setEnabled(false);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendClicked();
            }
        });

        return v;
    }

    public void updateSendButton(boolean isActive) {
        mSendButton.setEnabled(isActive);
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

            holder.mUserName.setText(chat.getUsername().getValue());
            holder.mMessage.setText(chat.getMessage());
        }

        @Override
        public int getItemCount() {
            return chats.size();
        }
    }
}

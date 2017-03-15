package com.example.duong.android_forder_01.data.source.remote;

import com.example.duong.android_forder_01.data.model.Avatar;
import com.example.duong.android_forder_01.data.model.CollectionAvatar;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.Thumbnail;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.DomainDataSource;
import com.example.duong.android_forder_01.data.source.GetDataCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 27/02/2017.
 */
public class DomainRemoteDataSource implements DomainDataSource {
    public static DomainRemoteDataSource sDomainRemoteDataSource;

    private DomainRemoteDataSource() {
    }

    public static DomainRemoteDataSource getInstance() {
        if (sDomainRemoteDataSource == null) {
            sDomainRemoteDataSource = new DomainRemoteDataSource();
        }
        return sDomainRemoteDataSource;
    }

    @Override
    public void getDatasDomain(int userId, final GetDataCallback getDataCallback) {
        if (getDataCallback == null) return;
        //TODO: Load domain
        // Faked data
        List<Domain> domainList = new ArrayList<>();
        int[] iddomain = {1, 2, 3, 4};
        String[] name = {"Cá nhân", "Framgia ĐN", "Framgia HN", "Framgia HCM"};
        for (int i = 0; i < iddomain.length; i++) {
            Domain domain = new Domain();
            domain.setId(iddomain[i]);
            domain.setName(name[i]);
            domainList.add(domain);
        }
        getDataCallback.onLoaded(domainList);
    }

    @Override
    public void getDatasPrivateDomainInfor(int userId, final GetDataCallback getDataCallback) {
        if (getDataCallback == null) return;
        //TODO: Load private domain
        // Faked data
        if (getDataCallback == null) return;
        //Domain
        List<Domain> domainList = new ArrayList<>();
        int[] iddomain = {1, 2, 3};
        String[] name = {"Cá nhân", "Framgia ĐN", "Test"};
        String[] member = {"1", "120", "0"};
        String[] shop = {"0", "2", "5"};
        String[] product = {"0", "15", "0"};
        int[] isjoined = {1, 1, 1};
        int[] isprivate = {1, 1, 0};
        int[] isowner = {1, 1, 0};
        int[] rootdomain = {1, 0, 0};
        //Member
        List<User> userList1 = new ArrayList<>();
        int[] userid1 = {1};
        String[] username1 = {"vinh@framgia.com"};
        String[] fullname1 = {"Nguen Tien Vinh"};
        int[] ismember1 = {1};
        int[] role1 = {0};
        int[] owner1 = {1};
        for (int j = 0; j < userid1.length; j++) {
            User user = new User();
            user.setId(userid1[j]);
            user.setUserName(username1[j]);
            user.setFullName(fullname1[j]);
            user.setIsMember(ismember1[j]);
            user.setRole(role1[j]);
            user.setOwner(owner1[j]);
            userList1.add(user);
        }
        List<User> userList2 = new ArrayList<>();
        int[] userid2 = {1, 2};
        String[] username2 = {"vinh@framgia.com", "duong@framgia.com"};
        String[] fullname2 = {"Nguen Tien Vinh", "Nguyen Minh Duong"};
        int[] ismember2 = {1, 1};
        int[] role2 = {0, 0};
        int[] owner2 = {1, 0};
        for (int j = 0; j < userid2.length; j++) {
            User user = new User();
            user.setId(userid2[j]);
            user.setUserName(username2[j]);
            user.setFullName(fullname2[j]);
            user.setIsMember(ismember2[j]);
            user.setRole(role2[j]);
            user.setOwner(owner2[j]);
            userList2.add(user);
        }
        List<User> userList3 = new ArrayList<>();
        int[] userid3 = {1, 3};
        String[] username3 = {"tri@framgia.com", "vinh@framgia.com"};
        String[] fullname3 = {"Tran Minh Tri", "Nguen Tien Vinh"};
        int[] ismember3 = {1, 1};
        int[] role3 = {0, 0};
        int[] owner3 = {1, 1};
        for (int j = 0; j < userid3.length; j++) {
            User user = new User();
            user.setId(userid3[j]);
            user.setUserName(username3[j]);
            user.setFullName(fullname3[j]);
            user.setIsMember(ismember3[j]);
            user.setRole(role3[j]);
            user.setOwner(owner3[j]);
            userList3.add(user);
        }
        List[] listUser = {userList1, userList2, userList3};
        //Shop
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setUrl("http://media.bizwebmedia.net/sites/76685/data/Upload/2014/8/do_an.jpg");
        Avatar avatar = new Avatar();
        avatar.setThumbnail(thumbnail);
        avatar.setUrl("http://media.bizwebmedia.net/sites/76685/data/Upload/2014/8/do_an.jpg");
        CollectionAvatar collectionAvatar = new CollectionAvatar();
        collectionAvatar.setAvatar(avatar);
        int[] idShop1 = {1};
        String[] nameShop1 = {"Shop tap nham"};
        String[] descriptionShop1 = {"Cai gi cung co"};
        List<Shop> shopList1 = new ArrayList<>();
        for (int i = 0; i < idShop1.length; i++) {
            Shop shop1 = new Shop();
            shop1.setId(idShop1[i]);
            shop1.setName(nameShop1[i]);
            shop1.setDescription(descriptionShop1[i]);
            shop1.setCollectionAvatar(collectionAvatar);
            shopList1.add(shop1);
        }
        for (int i = 0; i < iddomain.length; i++) {
            domainList.add(new Domain(iddomain[i], name[i], listUser[i], shopList1,
                member[i],
                shop[i],
                product[i], isjoined[i], isprivate[i], isowner[i], rootdomain[i]));
        }
        getDataCallback.onLoaded(domainList);
    }

    @Override
    public void getDatasPublicDomainInfor(int userId, final GetDataCallback getDataCallback) {
        if (getDataCallback == null) return;
        //TODO: Load public domain
        //Fake data
        List<Domain> domainList = new ArrayList<>();
        List<Shop> shopList = new ArrayList<>();
        int[] iddomain = {4, 5, 6};
        String[] name = {"Framgia HN", "Framgia HCM", "Test 2"};
        String[] numbermember = {"200", "150", "0"};
        String[] numbershop = {"10", "15", "5"};
        String[] numberproduct = {"50", "45", "0"};
        int[] isjoined = {0, 0, 0};
        int[] isprivate = {0, 0, 0};
        int[] isowner = {0, 0, 0};
        int[] rootdomain = {0, 0, 0};
        List<User> userList = new ArrayList<>();
        int[] userid = {1, 2, 3};
        String[] username = {"vinh@framgia.com", "duong@framgia.com", "tri@framgia.com"};
        String[] fullname = {"Nguen Tien Vinh", "Nguyen Minh Duong", "Tran Minh Tri"};
        int[] ismember = {1, 1, 1};
        int[] role = {1, 0, 0};
        for (int i = 0; i < userid.length; i++) {
            User user = new User();
            user.setId(userid[i]);
            user.setUserName(username[i]);
            user.setFullName(fullname[i]);
            user.setIsMember(ismember[i]);
            user.setRole(role[i]);
            userList.add(user);
        }
        for (int i = 0; i < iddomain.length; i++) {
            domainList.add(new Domain(iddomain[i], name[i], userList, shopList, numbermember[i],
                numbershop[i],
                numberproduct[i], isjoined[i], isprivate[i], isowner[i], rootdomain[i]));
        }
        getDataCallback.onLoaded(domainList);
    }
}

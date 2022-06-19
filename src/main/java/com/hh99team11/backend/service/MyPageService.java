package com.hh99team11.backend.service;

import com.hh99team11.backend.dto.ImgDto;
import com.hh99team11.backend.dto.ImgUrlDto;
import com.hh99team11.backend.dto.MyPageDto;
import com.hh99team11.backend.model.Img;
import com.hh99team11.backend.model.User;
import com.hh99team11.backend.repository.ImgRepository;
import com.hh99team11.backend.repository.UserRepository;
import com.hh99team11.backend.security.UserDetailsImpl;
import com.hh99team11.backend.util.exception.CustomException;
import com.hh99team11.backend.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final ImgRepository imgRepository;
    private final AwsS3UploadService s3UploadService;
    private final FileUploadService fileUploadService;


    @Transactional
    public MyPageDto.ResponseDto findAnimalInfo(Long userId) {

        // userPk 정보를 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_INFO)
        );

        List<String> imgUrlList = new ArrayList<>();


        if(!user.getUserImgList().isEmpty())
            for(Img imgList : user.getUserImgList()){
                imgUrlList.add(imgList.getImgUrl());
            }

        return MyPageDto.ResponseDto.builder()
                .petName(user.getPetName())
                .petSizeType(user.getPetSizeType())
                .animalAge(user.getAnimalAge())
                .address(user.getAddress())
                .lat(user.getLat())
                .lng(user.getLng())
                .animalImgUrlList(imgUrlList)
                .build();

    }

    @Transactional
    public void modifyAnimalInfo(Long userId, MyPageDto.RequestDto requestDto, List<MultipartFile> imgs, UserDetailsImpl userDetails) throws IOException {


        User user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_INFO)
        );

        //User가 가지고 있는 Img_Url을 전부 조회한다.
        List<Img> userImgList = user.getUserImgList();

        //삭제해야할 Img 을 담는다 .
        List<Img> removeImgList = new ArrayList<>();
        List<ImgDto> imgDtoList = new ArrayList<>();

        for(Img imgList : userImgList){

            for(ImgUrlDto imgUrlDto : requestDto.getCurrentImgUrl()){

                if(imgList.getImgUrl().equals(imgUrlDto.getImgUrl())){
                    //s3에서 삭제
                    s3UploadService.deleteFile(imgList.getImgName());
                    //해당 유저와 연관관계가 맺어진 이미지 레포에서도 해당 이미지들을 지운다.
                    imgRepository.deleteById(imgList.getId());
                    removeImgList.add(imgList);
                }
            }
        }

        // / removeImgList에 담긴 수정 이미지 찾아온 portfolioImgList 에서 제거
        for (Img userImg : removeImgList) {
            userImgList.remove(userImg);
        }


        //이미지가 Null 값이 아니라면
        if (imgs != null) {
            for (MultipartFile img : imgs) {
                if (!imgs.isEmpty()) {
                    // 파일 name과 url을 dto에 빌드
                    ImgDto imgDto = fileUploadService.uploadImage(img);
                    imgDtoList.add(imgDto);
                }
            }
        }
        updateImgParser(userImgList, imgDtoList);
        user.updateInfo(requestDto, userImgList);
    }

    private void updateImgParser(List<Img> imgList, List<ImgDto> imgDtoList) {
        for (ImgDto imgDto : imgDtoList) {
            Img img = Img.builder()
                    .imgName(imgDto.getImgName())
                    .imgUrl(imgDto.getImgUrl())
                    .build();
            imgList.add(img);
        }
    }
}



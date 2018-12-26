package db;

import java.util.List;

public class WriteDataBean {
	
	private WriteBoardDataBean boardDto;
	private List<WriteTripDataBean> tripDtoList;
	
	public WriteBoardDataBean getBoardDto() {
		return boardDto;
	}
	public void setBoardDto(WriteBoardDataBean boardDto) {
		this.boardDto = boardDto;
	}
	public List<WriteTripDataBean> getTripDtoList() {
		return tripDtoList;
	}
	public void setTripDtoList(List<WriteTripDataBean> tripDtoList) {
		this.tripDtoList = tripDtoList;
	}
}

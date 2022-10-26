package com.earts.earts.util;

import java.util.function.Consumer;
import java.util.Date;
import java.util.List;

public class SetterNullAware {
    
	public void setNumber(Consumer<? super Integer> consumer, Integer data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setBoolean(Consumer<? super Boolean> consumer, Boolean data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setDate(Consumer<? super Date> consumer, Date data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setString(Consumer<? super String> consumer, String data) {
		if(data != null)
			consumer.accept(data);
	}
	
	// public void setGender(Consumer<? super Gender> consumer, Gender data) {
	// 	if(data != null)
	// 		consumer.accept(data);
	// }
	
	// public void setArtwork(Consumer<? super ArtWork> consumer, ArtWork data) {
	// 	if(data != null)
	// 		consumer.accept(data);
	// }
	
	public void setByte(Consumer<? super Byte[]> consumer, Byte[] data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setListByte(Consumer<? super List<byte[]>> consumer, List<byte[]> data) {
		if(data != null)
			consumer.accept(data);
	}
	// public void setArtist(Consumer<? super Artist> consumer, Artist data) {
	// 	if(data != null)
	// 		consumer.accept(data);
	// }
}
